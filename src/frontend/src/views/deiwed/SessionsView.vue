<template>
    <v-card :flat="$store.getters.isMobile" class="table pb-4">
      <v-card-title>Sessões no DEI</v-card-title>
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
  import SessionDto from '@/models/deiwed/SessionDto';
  import RemoteServices from '@/services/RemoteServices';
  import { Component, Vue } from 'vue-property-decorator';
  import { DataTableHeader } from 'vuetify';
  
  @Component
  export default class SessionsView extends Vue {
    sessions: SessionDto[] = [];
    headers: DataTableHeader[] = [
      { text: 'ID', value: 'id', sortable: true, filterable: true },
      { text: 'Data', value: 'date', sortable: true, filterable: true },
      { text: 'Orador', value: 'speaker', sortable: true, filterable: true },
      { text: 'Tema', value: 'topic', sortable: true, filterable: false },
      // TODO: maybe add another column with possible actions? (edit / delete)
    ];
    search = '';
    loading = true;
  
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
  