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
          :items="dishes"
          :search="search"
          :loading="loading"
          single-select
          locale="pt-PT"
          no-data-text="Não existem pratos registados"
          no-results-text="Nenhum prato corresponde aos critérios indicados"
          sort-by="name"
        >
          <template v-slot:[`item.vegetarian`]="{ item }">
            <v-simple-checkbox v-model="item.vegetarian" disabled/>
          </template>
        </v-data-table>
      </v-card-text>
    </v-card>
  </template>
  
  <script lang="ts">
  import DishDto from '@/models/deiwed/DishDto';
  import RemoteServices from '@/services/RemoteServices';
  import { Component, Vue } from 'vue-property-decorator';
  import { DataTableHeader } from 'vuetify';
  
  @Component
  export default class DishesView extends Vue {
    dishes: DishDto[] = [];
    headers: DataTableHeader[] = [
      { text: 'Nome', value: 'name', sortable: true, filterable: true },
      { text: 'Preço (cêntimos)', value: 'unitPrice', sortable: true, filterable: true },
      { text: 'Vegetariano', value: 'vegetarian', sortable: true, filterable: false },
    ];
    search = '';
    loading = true;
  
    async mounted() {
      await this.$store.dispatch('loading');
      try {
        this.dishes = await RemoteServices.getDishes();
        this.loading = false;
      } catch (error) {
        this.$store.dispatch('error', error);
      }
      await this.$store.dispatch('clearLoading');
    }
  }
  </script>