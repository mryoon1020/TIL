<template>
  <div>
    <h1>글 목록</h1>
    <div>
      <table>
        <tr>
          <th>글번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>조회수</th>
          <th>작성시간</th>
          <th>수정시간</th>
        </tr>
        <tr>
          <td>1</td>
          <td>안녕하세요</td>
          <td>홍길동</td>
          <td>1</td>
          <td>2023-11-14</td>
        </tr>
        <tr>
          <td>2</td>
          <td>누구냐 넌</td>
          <td>관리자</td>
          <td>10</td>
          <td>2023-11-14</td>
        </tr>
      </table>
    </div>
      <router-link :to="{name:'boardCreate'}">
      <button>글쓰기</button>
      </router-link>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  setup () {
    const test = async () => {

      try {
        const res = await axios.get('http://localhost:8080');
        console.log(res);
      } catch {
        console.log("통신실패");
      }

    }

    test();

    return {
      test
    }
  }
}
</script>

<style>

</style>