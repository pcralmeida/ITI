<template>
    <v-card :flat="$store.getters.isMobile" class="table pb-4">
      <v-card-title>Pratos Disponíveis no DEI às Quartas</v-card-title>
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
          no-data-text="Não existem pratos registados"
          no-results-text="Nenhum prato corresponde aos critérios indicados"
          sort-by="name"
        >
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
        </v-data-table>
      </v-card-text>
    </v-card>
  </template>
  
  <script lang="ts">
  import AttendeeDto from '@/models/deiwed/AttendeeDto';
  import RemoteServices from '@/services/RemoteServices';
  import { Component, Vue } from 'vue-property-decorator';
  import { DataTableHeader } from 'vuetify';
  
  @Component
  export default class DishesView extends Vue {
    attendees: AttendeeDto[] = [];
    headers: DataTableHeader[] = [
      { text: 'Nome', value: 'name', sortable: true, filterable: true },
      { text: 'Preço', value: 'istId', sortable: true, filterable: true },
      { text: 'Vegetariano', value: 'type', sortable: true, filterable: false },
      // TODO: maybe add another column with possible actions? (edit / delete)
    ];
    search = '';
    loading = true;
  
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
  