<template>
  <div>
    <h1>글 목록</h1>
    <div>
      <table>
        <tr>
          <th>글번호</th>
          <th>제목</th>
          <th>작성시간</th>
          <th>수정시간</th>
        </tr>
        <tr
          v-for="(boardContent, index) in boardList"
          :key ="index"
        >
          <td> {{ boardContentNo - index }}</td>
          <td> {{ boardContent.boardTitle }} </td>
          <td> {{ boardContent.boardWriteDate }} </td>
          <td> {{ boardContent.boardUpdateDate }} </td>
        </tr>
      </table>
    </div>
      <router-link :to="{name:'boardCreate'}">
      <button>글쓰기</button>
      </router-link>
  </div>
</template>

<script>
import axiosForList from '@/axioses/boardAxioses.js'
export default {
  setup () {

    axiosForList.test

    return {
      test
      // boardList,
      // boardContentNo
    }
  }
}
</script>

<style>

</style>