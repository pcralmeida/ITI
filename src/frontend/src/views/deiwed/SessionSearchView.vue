<template>
    <div class="submit-form mt-16 pa-4 mx-auto">
      <p class="headline">Encontrar Sessão</p>
        <v-form ref="form" lazy-validation>
          <v-text-field
            v-model="tutorial.title"
            :rules="[(v) => !!v || 'Indique o ID da sessão']"
            label="ID da sessão"
            required
          ></v-text-field>
        </v-form>
  
        <v-btn color="primary" class="mt-4" to="/">Encontrar</v-btn>
      </div>
  </template>
  
  <script>
  import RemoteServices from '@/services/RemoteServices';
  
  export default {
    name: "add-tutorial",
    data() {
      return {
        tutorial: {
          id: null,
          title: "",
          description: "",
          published: false,
        },
        submitted: false,
      };
    },
    methods: {
      saveTutorial() {
        var data = {
          title: this.tutorial.title,
          description: this.tutorial.description,
        };
  
        TutorialDataService.create(data)
          .then((response) => {
            this.tutorial.id = response.data.id;
            console.log(response.data);
            this.submitted = true;
          })
          .catch((e) => {
            console.log(e);
          });
      },
  
      newTutorial() {
        this.submitted = false;
        this.tutorial = {};
      },
    },
  };
  </script>