import bannerBase64 from '@/utils/data/bannerBase64'

const copyright = {
    init: () => {
        console.log(
            `%c %c\n\n\n适合中文程序员的变量命名助手，规范变量命名，定制化变量命名规则\n\n`,
            ` padding: 60px 300px;
    background-image: url(${bannerBase64});
    background-size: contain;
    background-repeat: no-repeat;
    color: transparent;`,
            'color: #3eaf7c;font-size: 20px;margin-bottom: 20px;'
        )
    }
}

export default copyright
