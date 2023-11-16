<template>
    <div>
        <h1>글작성 페이지</h1>
        <div>
            <label for="">제목:</label>
            <BoardContentsInput />
        </div>
        <div>
            <label for="">내용:</label>
            <BoardContentsInput />
        </div>
        <div>
            <BoardListButton/>
            <button>수정</button>
            <button>완료</button>
            <button>취소</button>
        </div>
    </div>
</template>

<script>
import BoardContentsInput from '@/components/boardContentsInput.vue'
import BoardListButton from '@/components/boardListButton.vue'
export default {
    components : {
        BoardContentsInput,
        BoardListButton

    }
}
</script>

<style>

</style>