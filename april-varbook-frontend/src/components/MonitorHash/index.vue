<script setup>
import {ElMessage} from 'element-plus'

import {translate} from '@/api/translate'
import useSearch from '@/store/search'
import {checkContent} from '@/utils/character/standard'
import {hash, title} from '@/utils/window/location'

const search = useSearch()

const sendWarn = (message) => {
  ElMessage({
    showClose: true,
    message,
    duration: 4000,
    type: 'warning'
  })
}

const updateHash = (hashValue) => {
  if (!hashValue) return
  const {isValid, msg} = checkContent(hashValue)
  if (!isValid) {
    sendWarn(msg)
    return
  }
  if (hash.amend()) return

  search.setContent(hashValue)
  title.set(hashValue)
  const searchBody = {text: hashValue}
  search.setIsLoading(true)
  translate(searchBody)
      .then((res) => {
        search.setNamedVariables(res)
      })
      .catch((err) => {
        sendWarn('服务器扛不住啦～，请稍后再试')
        search.setIsLoading(false)
        hash.set('')
      })
}

// if (pathname.get() !== '/') pathname.set('/')
// else updateHash(hash.get())

window.onhashchange = () => {
  updateHash(hash.get())
}

search.setIsLoading(false)
</script>
