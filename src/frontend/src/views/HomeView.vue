<template>
    <div class="home">
      <v-container fluid fill-height>
        <v-layout justify-center align-center column pa-5>
            <div class="display-2 font-weight-black white--text text-xs-center">Quartas-feiras</div>
            <div class="ma-md-2 display-4 font-weight-black white--text text-xs-center mb-3">numa APP!</div>
            <div class="ma-md-25 display-0 font-weight-bold white--text text-xs-center">Gere as tuas quartas com a nova app DEIWed.</div>
        </v-layout>
    </v-container>
    </div>  
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component
export default class HomeView extends Vue {
  appName: string = process.env.VUE_APP_NAME;
}
</script>

<style scoped>
  .home {
    background-color: #009FE3;
    background-size: cover;
    height: 100vh;
    width: 100vw;
  }
</style>
