import { createRouter, createWebHistory } from 'vue-router';
import boardList from '@/pages/boardList.vue';
import boardCreate from '@/pages/boardCreate.vue';

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