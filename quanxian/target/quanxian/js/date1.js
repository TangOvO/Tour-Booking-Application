$(function () {
    var now = new Date();
    var currYear = now.getFullYear();
    var currMonth = now.getMonth() ;
    var currDay = now.getDate();
    var option = {
        preset: 'date', //日期，可选：date\datetime\time\tree_list\image_text\select
        theme: 'ios', //皮肤样式，可选：default\android\android-ics light\android-ics\ios\jqm\sense-ui\wp light\wp
        display: 'modal', //显示方式 ，可选：modal\inline\bubble\top\bottom
        lang: "Chinese",
        showLabel: true,//false  显示头部
        rows: 5,//显示多少行，定义3就显示3行,
        dateFormat: 'yyyy-mm-dd', // 面板日期格式
        setText: '确认', //确认按钮名称
        cancelText: '取消', //取消按钮名称
        dateOrder: 'yyyymmdd', //面板中日期排列格式
        dayText: '日',
        monthText: '月',
        yearText: '年', //面板中年月日文字
        // showNow: false,
        nowText: "今",
        endYear: currYear, //结束年份
        minDate: new Date(currYear, currMonth - 1, currDay  - 1),
        onSelect: function (textVale, inst) { //选中时触发事件
            console.log("我被选中了....." + textVale);
            $("#dateInput").val(textVale);

        },
        onClose: function (textVale, inst) { //插件效果退出时执行 inst:表示点击的状态反馈：set/cancel
            console.log("textVale--"  + textVale);
            console.log(this.id);//this表示调用该插件的对象
        }

        //wheels:[[11,12,13],[21,22,23],[01,02,03]],// 当前你定义的数组(即要滚动的数组),
        //defaultValue: [12,22]//默认显示当前滚动到哪个值,
        // formatResult://滚动到某个值后执行某个方法
        // function(){
        //   console.log(1);
        // }
    }

    //面板显示日期
    $("#demo").mobiscroll().calendar(option);
    //$("#demo").mobiscroll("show");

    //面板显示时间
    // $("#input").mobiscroll().time(option);

    /*$("#clear").click(function () {
      $("#input").mobiscroll("clear");
      return false;
    });

    $("#show").click(function () {
      $("#input").mobiscroll("show");
      return false;
    });*/
});

// 加减数量
var addSum = function() {
    // 按钮事件
    var jian = document.getElementsByClassName('jian');
    var shu = document.getElementsByClassName('shu');
    var jia = document.getElementsByClassName('jia');
    for(var i = 0; i < jia.length; i++) {
        jian[i].shu = shu[i];
        jia[i].shu = shu[i];
        jia[i].jian = jian[i];
        jian[i].onclick = function() {
            var n = parseInt(this.shu.value)
            if(n > 1) {
                n--;
            }
            this.shu.value = n;
        };
        jia[i].onclick = function() {
            var n = parseInt(this.shu.value)
            n++;
            this.shu.value = n;
        };
    }

}

//调用函数
//addSum();