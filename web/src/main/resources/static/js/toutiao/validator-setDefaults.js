$.validator.setDefaults({
    errorPlacement:function(error,element) {
        if(element.is(':radio') || element.is(':checkbox')){
            $('.validator-error-box').addClass('text-danger');
        } else
            error.insertAfter(element);
    },
    errorClass: 'help-error',
    errorElement: 'div',
    success: function (_,element) {
        element=$(element);
        if(element.is(':radio') || element.is(':checkbox')){
            element.closest('.text-danger').removeClass('text-danger');
        }
    }
});