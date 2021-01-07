<template>
    <div class="subListComponent">
        <h1 class="title">Subscription List</h1>
        <div class="tableContainer">
            <v-simple-table>
                <template v-slot:default>
                    <thead>
                        <tr>
                            <th class="text-left">
                                Reporter
                            </th>
                            <th class="text-left">
                                NewsLetter
                            </th>
                            <th class="text-left">
                                Subscribe
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr
                        v-for="item in desserts"
                        :key="item.name"
                        >
                            <td>이푸름</td>
                            <td>토글버튼</td>
                            <td>취소버튼</td>
                        </tr>
                    </tbody>
                </template>
            </v-simple-table>
        </div>
    </div>
</template>

<script>
export default {
    data () {
      return {
        desserts: [
          {
            name: 'Frozen Yogurt',
            calories: 159,
          },
          {
            name: 'Ice cream sandwich',
            calories: 237,
          },
          {
            name: 'Eclair',
            calories: 262,
          },
          {
            name: 'Cupcake',
            calories: 305,
          },
          {
            name: 'Gingerbread',
            calories: 356,
          },
          {
            name: 'Jelly bean',
            calories: 375,
          },
          {
            name: 'Lollipop',
            calories: 392,
          },
          {
            name: 'Honeycomb',
            calories: 408,
          },
          {
            name: 'Donut',
            calories: 452,
          },
          {
            name: 'KitKat',
            calories: 518,
          },
        ],
      }
    },
}
</script>

<style scoped>
.tableContainer{
    margin-top: 50px;
}

</style>