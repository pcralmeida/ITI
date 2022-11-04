<template>
    <v-card :flat="$store.getters.isMobile" class="table pb-4">
      <v-card-title>Participantes no DEI às Quartas</v-card-title>
      <v-card-text>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Pesquisar"
          single-line
          hide-details
        ></v-text-field>
        <v-data-table
          :headers="headers"
          :items="attendees"
          :search="search"
          :loading="loading"
          single-select
          locale="pt-PT"
          no-data-text="Não existem participantes registados"
          no-results-text="Nenhum participante corresponde aos critérios indicados"
          sort-by="name"
        >
          <template v-slot:[`item.actions`]="{ item }">
              <v-icon small class="mr-2">mdi-pencil</v-icon>
              <v-icon small @click="deleteAttendee(item.id)">mdi-delete</v-icon>
          </template>
          <template v-slot:[`item.type`]="{ item }">
            <v-chip
              v-if="item.type === 'TEACHER'"
              color="purple"
              text-color="white"
            >
              Professor
            </v-chip>
            <v-chip v-else color="green" text-color="white"> Bolseiro </v-chip>
          </template>
          <template v-slot:[`item.vegetarian`]="{ item }">
            <v-simple-checkbox v-model="item.vegetarian" disabled/>
          </template>
        </v-data-table>
      </v-card-text>
      <v-dialog
            v-model="dialog"
            max-width="500px"
            persistent
          >
          <template v-slot:activator="{ on, attrs }">
              <v-btn
                color="primary"
                class="mb-2"
                v-bind="attrs"
                v-on="on"
              >
                Adicionar Participante
              </v-btn>
          </template>
          <v-card>
          <v-card-title>
            <span class="text-h5">Adicionar Participante</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                  <v-text-field
                  
                    label="Nome*"
                    required
                    hint="Nome do participante"
                  ></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                  <v-text-field
                  
                    label="IST ID*"
                    required
                    hint="Número IST do participante"
                  ></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                <v-select
                
                    :items="['Bolseiro', 'Professor']"
                    label="Tipo*"
                    required
                  ></v-select>
                </v-col>
                <v-col cols="12">
                  <v-text-field
           
                    label="Email*"
                    required
                    hint="Email do participante"
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field
         
                    label="Observações"
                    hint="Observações sobre o participante"
                  ></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                >
                  <v-select
      
                    :items="['Sim', 'Não']"
                    label="Vegetariano*"
                    required
                  ></v-select>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                >
                  <v-autocomplete
     
                    :items="['Glúten', 'Lactose', 'Outro']"
                    label="Intolerâncias"
                    multiple
                  ></v-autocomplete>
                </v-col>
              </v-row>
            </v-container>
            <small>*preenchimento necessário</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="blue darken-1"
              text
              @click="dialog = false"
            >
              Close
            </v-btn>
            <v-btn
              color="blue darken-1"
              text
              @click="/*createAttendee({attendee.id, attendee.istId, attendee.type, attendee.email, attendee.vegetarian, attendee.intolerances, attendee.observations}),*/ dialog = false"
            >
              Save
            </v-btn>
          </v-card-actions>
        </v-card>
       </v-dialog>
    </v-card>
  </template>
  
  <script lang="ts">
  import AttendeeDto from '@/models/deiwed/AttendeeDto';
  import RemoteServices from '@/services/RemoteServices';
  import { Component, Vue } from 'vue-property-decorator';
  import { DataTableHeader } from 'vuetify';
  
  @Component
  export default class AttendeesView extends Vue {
    attendees: AttendeeDto[] = [];
    headers: DataTableHeader[] = [
      { text: 'ID', value: 'id', sortable: true, filterable: true },
      { text: 'Nome', value: 'name', sortable: true, filterable: true },
      { text: 'IST ID', value: 'istId', sortable: true, filterable: true },
      { text: 'Tipo', value: 'type', sortable: true, filterable: false },
      { text: 'Email', value: 'email', sortable: true, filterable: false },
      { text: 'Vegetariano', value: 'vegetarian', sortable: true, filterable: true },
      { text: 'Intolerâncias', value: 'intolerances', sortable: true, filterable: true },
      { text: 'Observações', value: 'observations', sortable: true, filterable: true },
      { text: 'Ações', value: 'actions', sortable: false, filterable: false },
    ];
    dialog = false;
    search = '';
    loading = true;
  
    async createAttendee(attendeeDto: AttendeeDto) {
      await this.$store.dispatch('loading');
      try {
        this.attendees = await RemoteServices.createAttendee(attendeeDto);
        this.loading = false;
      } catch (error) {
        this.$store.dispatch('error', error);
      }
      await this.$store.dispatch('clearLoading');
    }
  
    async updateAttendee(id: number, attendeeDto: AttendeeDto) {
      await this.$store.dispatch('loading');
      try {
        this.attendees = await RemoteServices.updateAttendee(id, attendeeDto);
        this.loading = false;
      } catch (error) {
        this.$store.dispatch('error', error);
      }
      await this.$store.dispatch('clearLoading');
    }
  
    async deleteAttendee(id: number) {
      await this.$store.dispatch('loading');
      try {
        this.attendees = await RemoteServices.deleteAttendee(id);
        this.loading = false;
      } catch (error) {
        this.$store.dispatch('error', error);
      }
      await this.$store.dispatch('clearLoading');
    }
  
    async mounted() {
      await this.$store.dispatch('loading');
      try {
        this.attendees = await RemoteServices.getAttendees();
        this.loading = false;
      } catch (error) {
        this.$store.dispatch('error', error);
      }
      await this.$store.dispatch('clearLoading');
    }
  }
  </script>
  