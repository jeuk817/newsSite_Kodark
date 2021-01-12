<template>
  <div class="reporterListComponent">
    <h1 class="title">Reporter List</h1>
    <v-simple-table
    fixed-header
    height="300px"
    >
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">
              ID
            </th>
            <th class="text-left">
              Name
            </th>
            <th class="text-left">
              Total Hit
            </th>
            <th class="text-left">
              Subscriber Number
            </th>
            <th class="text-left">
              articleNum
            </th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="item in reporterList"
            :key="item.id"
          >
            <td>{{ item.id }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.subNum }}</td>
            <td>{{ item.totalHit }}</td>
            <td>{{ item.articleNum }}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </div>
</template>

<script>
export default {
  data: () => ({
    reporterList : []

  }),
  async created () {
    console.log("created")
    const {status, reporters } = await this.$store.dispatch('admin/getReporters');
    if(status === 200) {
      console.log(typeof(reporters))
      console.log(reporters);
      this.reporterList = reporters;
      console.log(this.reporterList);
    }
    if(status === 204){
      //에러처리필요
    }
  }
}
</script>

<style scoped>
.title{
  padding: 10px 10px 10px 0 ;
  border-bottom: 1px solid black;
  margin-bottom: 50px;
}
</style>