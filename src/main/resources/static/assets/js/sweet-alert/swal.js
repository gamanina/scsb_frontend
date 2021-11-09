// 刪除警告
$(document).on('click', '.delwarning', function(e) {
    swal({
        title: "確定要刪除嗎?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
    .then((willDelete) => {
        if (willDelete) {
            swal("已刪除", {
                icon: "success",
            });
        } else {
            // swal("取消刪除");
        }
    })
});

// 購物車送出訂單警告
$(document).on('click', '.orderconfirm', function(e) {
    swal({
        title: "確定要發送訂單嗎?",
        text: "注意：訂單送出後，就無法再進行修改",
        icon: "info",
        buttons: true,
        dangerMode: true,
    })
    .then((orderConfirm) => {
        if (orderConfirm) {
            swal("訂單已送出", {
                icon: "success",
            });
        } else {
            // swal("取消發送");
        }
    })
});

// 砍單警告
$(document).on('click', '.cutwarning', function(e) {
    swal({
        title: "確定要砍單嗎?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
    .then((willDelete) => {
        if (willDelete) {
            swal("已砍單", {
                icon: "success",
            });
        } else {
            // swal("取消刪除");
        }
    })
});

// 發生錯誤提示
$(document).on('click', '.error', function(e) {
    swal({
        title: "發生錯誤",
        text: " ",
        icon: "error",
        timer: 800,
        buttons: false,
        }).then(
        function () {},
        // handling the promise rejection
        function (dismiss) {
            if (dismiss === 'timer') {
            //console.log('I was closed by the timer')
            }
        }
        )
    });

// 失敗提示
$(document).on('click', '.failure', function(e) {
    swal({
        title: "失敗",
        text: " ",
        icon: "error",
        timer: 800,
        buttons: false,
        }).then(
        function () {},
        // handling the promise rejection
        function (dismiss) {
            if (dismiss === 'timer') {
            //console.log('I was closed by the timer')
            }
        }
        )
    });

// 成功提示
$(document).on('click', '.successful', function(e) {
    swal({
        title: "成功",
        text: " ",
        icon: "success",
        timer: 800,
        buttons: false,
        }).then(
        function () {},
        // handling the promise rejection
        function (dismiss) {
            if (dismiss === 'timer') {
            //console.log('I was closed by the timer')
            }
        }
        )
    });

// 成功加入購物車提示
$(document).on('click', '.successcart', function(e) {
    swal({
        title: "已加入購物車",
        text: " ",
        icon: "success",
        timer: 800,
        buttons: false,
        }).then(
        function () {},
        // handling the promise rejection
        function (dismiss) {
            if (dismiss === 'timer') {
            //console.log('I was closed by the timer')
            }
        }
        )
    });

// 產品修改成功提示
$(document).on('click', '.editsuccess', function(e) {
    swal({
        title: "修改成功",
        text: " ",
        icon: "success",
        timer: 800,
        buttons: false,
        }).then(
        function () {},
        // handling the promise rejection
        function (dismiss) {
            if (dismiss === 'timer') {
            //console.log('I was closed by the timer')
            }
        }
        )
    });

// 上傳提示
$(document).on('click', '.uploadalert', function(e) {
    swal({
        title: "上傳成功",
        text: " ",
        icon: "success",
        timer: 800,
        buttons: false,
        }).then(
        function () {},
        // handling the promise rejection
        function (dismiss) {
            if (dismiss === 'timer') {
            //console.log('I was closed by the timer')
            }
        }
        )
    });

// 下載提示
$(document).on('click', '.downloadalert', function(e) {
    swal({
        title: "下載成功",
        text: " ",
        icon: "success",
        timer: 800,
        buttons: false,
        }).then(
        function () {},
        // handling the promise rejection
        function (dismiss) {
            if (dismiss === 'timer') {
            //console.log('I was closed by the timer')
            }
        }
        )
    });