$(function() {
    window.location.hash = '';

    loadAndChangeLanguage(getLanguage());

    $('.help-block').hide();

    $('#remember').labelauty({
        label: false
    });

    $('#login-btn').click(function() {
        var $username = $('#username');
        var $password = $('#password');
        var $usernameHelp = $('#username-help');
        var $passwordHelp = $('#password-help');

        $username.closest('.form-group').removeClass('has-error');
        $password.closest('.form-group').removeClass('has-error');
        $usernameHelp.hide();
        $passwordHelp.hide();

        var hasError = false;
        var username = $username.val();
        var password = $password.val();

        if ($.trim(username) === '') {
            $username.closest('.form-group').addClass('has-error');
            $usernameHelp.show();
            hasError = true;
        }

        if ($.trim(password) === '') {
            $password.closest('.form-group').addClass('has-error');
            $passwordHelp.show();
            hasError = true;
        }

        if (hasError) {
            return false;
        }

        $.ajax({
            type: 'POST',
            url: '/login',
            contentType: 'application/json',
            data: JSON.stringify({
                username: username,
                password: password
            }),
            dataType: 'json',
            success: function(data) {
                if (data && data.code === 1 && data.result) {
                    window.location.href = data.location;
                } else {
                    showFailureMessage(locale_messages.login_username_password_error);
                }
            },
            error: function(req, err, cause) {
                showFailureMessage(locale_messages.common_alert_error_content);
            }
        });

        return false;
    });

    function showFailureMessage(message) {
        var $msg = $('.login-box-msg');

        $msg.css({
            color: '#dd4b39'
        });

        $msg.text(message);
    }
});