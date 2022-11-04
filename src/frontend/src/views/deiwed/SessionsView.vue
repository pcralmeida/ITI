<template>
  <v-card :flat="$store.getters.isMobile" class="table pb-4">
    <v-card-title>Sessões no DEI às Quartas</v-card-title>
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
        :items="sessions"
        :search="search"
        :loading="loading"
        single-select
        locale="pt-PT"
        no-data-text="Não existem sessões registadas"
        no-results-text="Nenhuma sessão corresponde aos critérios indicados"
        sort-by="name"
      >
        <template v-slot:[`item.actions`]="{ item }">
            <v-icon small class="mr-2">mdi-pencil</v-icon>
            <v-icon small @click="deleteSession(item.id);">mdi-delete</v-icon>
        </template>
      </v-data-table>
    </v-card-text>
      <v-form ref="form" lazy-validation>
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
                Adicionar Sessão
              </v-btn>
          </template>
          <v-card>
          <v-card-title>
            <span class="text-h5">Adicionar Sessão</span>
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
                    v-model="session.date"
                    :rules="rule"
                    label="Data*"
                    required
                    hint="AAAA-MM-DD"
                  ></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                  <v-text-field
                    v-model="session.speaker"
                    :rules="rule"
                    label="Orador*"
                    required
                    hint="Nome do orador"
                  ></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                  <v-text-field
                    v-model="session.topic"
                    :rules="rule"
                    label="Tema*"
                    required
                    hint="Tema da sessão"
                  ></v-text-field>
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
              Fechar
            </v-btn>
            <v-btn
              color="blue darken-1"
              text
              @click="createSession(session); dialog = false"
            >
              Guardar
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-form>
  </v-card>
</template>
  
  <script lang="ts">
  import SessionDto from '@/models/deiwed/SessionDto';
  import RemoteServices from '@/services/RemoteServices';
  import { Component, Vue } from 'vue-property-decorator';
  import { DataTableHeader } from 'vuetify';
  
  @Component
  export default class SessionsView extends Vue {
    sessions: SessionDto[] = [];
    headers: DataTableHeader[] = [
      { text: 'Data', value: 'date', sortable: true, filterable: true },
      { text: 'Orador', value: 'speaker', sortable: true, filterable: true },
      { text: 'Tema', value: 'topic', sortable: true, filterable: false },
      { text: 'Ações', value: 'actions', sortable: false, filterable: false },
    ];
    dialog = false;
    search = '';
    loading = true;
    rule = [(v: string) => !!v || 'Campo obrigatório'];
    session: SessionDto = {
      id: 0,
      date: '',
      speaker: '',
      topic: '',
    };
  
    async createSession(sessionDto: SessionDto) {
    await this.$store.dispatch('loading');
    try {
      this.sessions = await RemoteServices.createSession(sessionDto);
      this.loading = false;
    } catch (error) {
      this.$store.dispatch('error', error);
    }
    await this.$store.dispatch('clearLoading');
  }

  async updateSession(id: number, sessionDto: SessionDto) {
    await this.$store.dispatch('loading');
    try {
      this.sessions = await RemoteServices.updateSession(id, sessionDto);
      this.loading = false;
    } catch (error) {
      this.$store.dispatch('error', error);
    }
    await this.$store.dispatch('clearLoading');
  }

  async deleteSession(id: number) {
    await this.$store.dispatch('loading');
    try {
      this.sessions = await RemoteServices.deleteSession(id);
      this.loading = false;
    } catch (error) {
      this.$store.dispatch('error', error);
    }
    await this.$store.dispatch('clearLoading');
  }
  
    async mounted() {
      await this.$store.dispatch('loading');
      try {
        this.sessions = await RemoteServices.getSessions();
        this.loading = false;
      } catch (error) {
        this.$store.dispatch('error', error);
      }
      await this.$store.dispatch('clearLoading');
    }
  }
  </script>
  