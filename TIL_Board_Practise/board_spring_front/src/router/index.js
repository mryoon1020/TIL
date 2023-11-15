import { createRouter, createWebHistory } from 'vue-router';
import boardList from '@/pages/board_list.vue';
import boardCreate from '@/pages/board_create.vue';

const router = createRouter({
    history: createWebHistory(),
    routes : [
        {
            path: '/',
            name: 'boardList',
            component: boardList
        },
        {
            path: '/boardCreate',
            name: 'boardCreate',
            component: boardCreate
        }
    ]
})

export default router;