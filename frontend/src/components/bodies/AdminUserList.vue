<template>
  <div class="AdminUserListComponent">
    <h1 class="title">User List</h1>
     <v-simple-table
  >
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">
            ID
          </th>
          <th class="text-left">
            E-mail
          </th>
          <th class="text-left">
            Suspended
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="item in desserts"
          :key="item.name"
        >
          <td>{{ item.name }}</td>
          <td>{{ item.calories }}</td>
          <td>{{ item.suspended }}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
  </div>
</template>

<script>
export default {
 data: () => ({
       desserts: [
          {
            name: 'Frozen Yogurt',
            calories: 'lpr0818@gmail.com',
            suspended: true
          },
          {
            name: 'Ice cream sandwich',
            calories: 'lpr0818@gmail.com',
            suspended: true
          },
          {
            name: 'Eclair',
            calories: 'lpr0818@gmail.com',
            suspended: true
          },
          {
            name: 'Cupcake',
            calories: 'lpr0818@gmail.com',
            suspended: true
          },
          {
            name: 'Gingerbread',
            calories: 'lpr0818@gmail.com',
            suspended: true
          }
        ]
    }),
}
</script>

<style scoped>
.title{
  padding: 10px 10px 10px 0 ;
  border-bottom: 1px solid black;
  margin-bottom: 50px;
}
</style>