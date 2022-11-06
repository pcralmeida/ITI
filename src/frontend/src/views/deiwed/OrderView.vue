<template>
  <v-card :flat="$store.getters.isMobile" class="table pb-4">
    <v-card-title>Realize o seu pedido</v-card-title>
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
                Adicionar Pedido
              </v-btn>
          </template>
          <v-card>
          <v-card-title>
            <span class="text-h5">Adicionar Pedido</span>
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
                    v-model="date"
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
                    v-model="order.normalOptionDishId"
                    label="ID (prato normal)*"
                    required
                    hint="Dica: pode pesquisar o prato na tabela acima"
                  ></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                  <v-text-field
                    v-model="order.vegetarianOptionDishId"
                    label="ID (prato vegetariano)*"
                    required
                    hint="Dica: pode pesquisar o prato na tabela acima"
                  ></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                </v-col>  
                <v-col
                  cols="12"
                  sm="6"
                >
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
              @click="createOrder(date, order); dialog = false"
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
import DishDto from '@/models/deiwed/DishDto';
import OrderDto from '@/models/deiwed/OrderDto';
import RemoteServices from '@/services/RemoteServices';
import { Component, Vue } from 'vue-property-decorator';
import { DataTableHeader } from 'vuetify';

@Component
export default class OrderView extends Vue {
  dishes: DishDto[] = [];
  orders: OrderDto[] = [];
  headers: DataTableHeader[] = [
    { text: 'ID', value: 'id', sortable: true, filterable: true },
    { text: 'Nome', value: 'name', sortable: true, filterable: true },
    { text: 'Preço (cêntimos)', value: 'unitPrice', sortable: true, filterable: true },
    { text: 'Vegetariano', value: 'vegetarian', sortable: true, filterable: false },
  ];
  search = '';
  loading = true;
  dialog = false;
  date = '';

  order: OrderDto = {
      normalOptionDishId: 0,
      vegetarianOptionDishId: 0,
  };

  async createOrder(date: String, orderDto: OrderDto) {
    await this.$store.dispatch('loading');
    try {
      this.orders = await RemoteServices.createOrder(date, orderDto);
      this.loading = false;
    } catch (error) {
      this.$store.dispatch('error', error);
    }
    await this.$store.dispatch('clearLoading');
  }

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