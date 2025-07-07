import {createPinia} from 'pinia'
import {createApp} from 'vue'

import copyright from '@/utils/window/copyright'
import {color} from '@/utils/window/theme'

import VarBook from './app.vue'

color.auto()
copyright.init()

createApp(VarBook).use(createPinia()).mount('var-book')
