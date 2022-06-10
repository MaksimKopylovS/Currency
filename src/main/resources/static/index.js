angular.module('app', []).controller('indexController', function ($scope, $http) {
    let codeCurrency;
    /*Проверяем поле ввода на корректность*/
    $scope.isCurrency = function () {
        codeCurrency = $scope.currency;
        /*Проверка на то, что должно быть введено именно 3 больших EN символа */
        var re = /[A-Z]{3}/;
        if (re.test(codeCurrency)) {
            $scope.status = "Code правильный";
            fillTable(codeCurrency)
        } else $scope.status = "Ведите 3 заглавные буквы EN";
        if (isEmpty(codeCurrency)) $scope.status = "Ведите 3 заглавные буквы EN";
    }

    /*Проверка на то что сообщение длинной не меньше нуля и не больше 3 символов*/
    function isEmpty(str) {
        return (str == null) || (str.length == 0 || str.length > 3);
    }

    /*Отправка на сервер GET запроса*/
    function fillTable(codeCurrency) {
        $http.get('http://localhost:8080/api/currency/' + codeCurrency)
            .then(function (response) {
                console.log(response.data.url);
                $scope.code = response.data
                document.getElementById("image").src = response.data.url;
            });
    }
});




