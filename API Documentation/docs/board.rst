Board api
======================

/board/add_board/<string:board_string> (POST)
-----------------------------------------------

    **새로운 board를 추가**

    :Paramaters:

        **board_string**

        - name: board_string
        - in: path
        - description: 추가할 board의 이름
        - required: true


    **Example request**

    .. sourcecode:: http

        POST /board/add_board/softmax_regression HTTP/1.1
        Host: api.gistory.me

    **Example response**

    .. sourcecode:: http

        HTTP/1.1 201 OK
        Vary: Accept

    :resheader Content-Type: application/json
    :status:
        - 200: 성공적으로 요청을 수행함
        - 400: board가 이미 존재함
        - 500: 알 수 없는 오류 발생

/board/delete_board/<string:board_string> (DELETE)
------------------------------------------------------

    **board를 삭제(board에 연경되어 있던 content들은 남아있지만 반환되지 못함**

    :Paramaters:

        **board_string**

        - name: board_string
        - in: path
        - description: 삭제할 board의 이름
        - required: true


    **Example request**

    .. sourcecode:: http

        DELETE /board/delete_board/errorboard HTTP/1.1
        Host: api.gistory.me

    **Example response**

    .. sourcecode:: http

        HTTP/1.1 200 OK

    :resheader Content-Type: application/json
    :status:
        - 200: 성공적으로 요청을 수행함
        - 404: 삭제하려는 board가 존재하지 않음
        - 500: 알 수 없는 오류 발생

/board/modify_board/<string:board_string> (PUT)
--------------------------------------------------

    **board의 이름을 변경**

    :Paramaters:

        **board_string**

        - name: board_string
        - in: path
        - description: 수정할 board의 이름
        - required: true


    **Example request**

    .. sourcecode:: http

        PUT /board/modify_board/softmax_regression HTTP/1.1
        Host: api.gistory.me
        Content-Type: application/json

            {
                "new_board_string": "logistic_regression"
            }

    **Example response**

    .. sourcecode:: http

        HTTP/1.1 200 OK

    :resheader Content-Type: application/json
    :status:
        - 200: 성공적으로 요청을 수행함
        - 404: 수정하려는 board가 존재하지 않음
        - 500: 알 수 없는 오류 발생

/board/get_board_list (GET)
-------------------------------------

    **모든 board list를 반환**

    **Example request**

    .. sourcecode:: http

        GET /board/get_board_list HTTP/1.1
        Host: api.gistory.me
        Accept: application/json

    **Example response**

    .. sourcecode:: http

        HTTP/1.1 200 OK
        Content-Type: application/json

            {
                {
                    "id": 0,
                    "description": "machine learning"
                },
                {
                    "id": 1,
                    "description": "linear regression"
                },
                {
                    "id": 2,
                    "description": "Logistic regression"
                }
            }

    :resheader Content-Type: application/json
    :status:
        - 200: 성공적으로 요청을 수행함
        - 500: 알 수 없는 오류 발생