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
        <template v-slot:[`item.actions`]="{ item }">
            <v-icon small class="mr-2" @click="updateSession(item.id)">mdi-pencil</v-icon>
            <v-icon small @click="deleteSession(item.id)">mdi-delete</v-icon>
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
      { text: 'Data', value: 'date', sortable: true, filterable: true },
      { text: 'Orador', value: 'speaker', sortable: true, filterable: true },
      { text: 'Tema', value: 'topic', sortable: true, filterable: false },
      { text: 'Ações', value: 'actions', sortable: false, filterable: false },
    ];
    search = '';
    loading = true;
  
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
  