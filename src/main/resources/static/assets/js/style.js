// checkAll
// Statistics-situation-2.html
// Statistics-operation-time-2.html
// Statistics-store-2.html
// Statistics-weekly-growth-2.html
$("#checkAllZO").click(function () {
    $(".checkAllZO").prop('checked', $(this).prop('checked'));
});
$("#checkAllDO").click(function () {
    $(".checkAllDO").prop('checked', $(this).prop('checked'));
});
$("#checkAllOFC").click(function () {
    $(".checkAllOFC").prop('checked', $(this).prop('checked'));
});
$("#checkAllshop").click(function () {
    $(".checkAllshop").prop('checked', $(this).prop('checked'));
});

// Statistics-help-1.html
// Statistics-operation-time-1.html
// Statistics-situation-1.html
// Statistics-time-national-1.html
$("#checkAllbusiness").click(function () {
    $(".checkAllbusiness").prop('checked', $(this).prop('checked'));
});
$("#checkAllbusiness2").click(function () {
    $(".checkAllbusiness2").prop('checked', $(this).prop('checked'));
});

// Password fontawesome icon Show/Hide
$(".toggle-password").click(function() {
    $(this).toggleClass("fa-eye fa-eye-slash");
    var input = $($(this).attr("toggle"));
    if (input.attr("type") == "password") {
        input.attr("type", "text");
    } else {
        input.attr("type", "password");
    }
});

// formReset
function formReset(){
    document.getElementsByClassName("formReset")[0].reset()
}

// uploadFile
$('#up_imgfile').change(function(){
    $('#show_imgfile').val(this.value);
})
$('#up_imgfileMobile').change(function(){
    $('#show_imgfileMobile').val(this.value);
})
$('#up_cimpfile').change(function(){
    $('#show_cimpfile').val(this.value);
})

$('#up_attachedfile01').change(function(){
    $('#show_attachedfile01').val(this.value);
})
$('#up_attachedfile02').change(function(){
    $('#show_attachedfile02').val(this.value);
})
$('#up_attachedfile03').change(function(){
    $('#show_attachedfile03').val(this.value);
})
$('#up_attachedfile04').change(function(){
    $('#show_attachedfile04').val(this.value);
})
$('#up_attachedfile05').change(function(){
    $('#show_attachedfile05').val(this.value);
})

// datepicker
$( function() {
    var dateFormat = "yy/mm/dd",
    from = $(".fromdate")
        .datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        dateFormat: dateFormat,
        minDate: new Date(),
        numberOfMonths: 2
    })
    .on( "change", function() {
        var toMinDate = getDate(this);
        toMinDate.setDate(toMinDate.getDate() + 1);
        to.datepicker("option", "minDate", toMinDate);
    }),
    to = $( ".todate" ).datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        dateFormat: dateFormat,
        minDate: new Date(),
        numberOfMonths: 2
    })
    .on( "change", function() {
        var fromMaxDate = getDate(this);
        fromMaxDate.setDate(fromMaxDate.getDate() - 1);
        from.datepicker("option", "maxDate", fromMaxDate);
    });
    function getDate( element ) {
        var date;
        try {
            date = $.datepicker.parseDate(dateFormat, element.value);
            console.log(dateFormat);
            console.log(element.value);
        } catch( error ) {
            date = null;
        }
        return date;
    }
});

// datepicker from to今天以後不能選
$(function () {
    var dateFormat = "yy/mm/dd";
    $("#fromNodate").datepicker({
        dateFormat: dateFormat
    });
    $("#toNodate").datepicker({
        dateFormat: dateFormat
    });

    $("#fromNodate").datepicker("option", "maxDate", new Date());
    $("#toNodate").datepicker();
});

// chosen-select
$('.chosen-select').chosen({
    allow_single_deselect: true
});