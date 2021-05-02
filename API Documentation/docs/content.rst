Content api
===============

/content/add_content (POST)
-------------------------------------

    **새 설명문을 입력**

    :Paramaters:

        **board_string**

        - name: board_string
        - in: json
        - description: 입력할 설명문의 board 이름
        - required: true

        **content**

        - name: content
        - in: json
        - description: 입력할 설명문의 본문
        - required: true

    **Example request**:

    .. sourcecode:: http

        POST /content/add_content HTTP/1.1
        Host: localhost
        Content-Type: application/json

            {
                "board_string": "linear_regression",
                "content": "linear regression is good!"
            }

    **Example response**:

    .. sourcecode:: http

        HTTP/1.1 200 OK

:resheader Content-Type: application/json
:status:
    - 200: 성공적으로 요청을 수행함
    - 400: 필요한 매개변수가 없음
    - 404: 넣으려는 board가 존재하지 않음
    - 500: 알 수 없는 오류 발생

/content/delete_content/<int:content_id> (DELETE)
--------------------------------------------------

    **설명문을 삭제**

    :Paramaters:

        **content_id**

        - name: content_id
        - in: path
        - description: 삭제할 설명문의 ID
        - required: true

    **Example request**:

    .. sourcecode:: http

        DELETE /content/delete_content/1 HTTP/1.1
        Host: localhost

    **Example response**:

    .. sourcecode:: http

        HTTP/1.1 200 OK

:resheader Content-Type: application/json
:status:
    - 200: 성공적으로 요청을 수행함
    - 404: 삭제하려는 설명문이 존재하지 않음
    - 500: 알 수 없는 오류 발생

/content/modify_content/<int:content_id> (PUT)
------------------------------------------------

    **특정 설명문을 수정**

    :Paramaters:

        **content_id**

        - name: content_id
        - in: path
        - description: 수정할 설명문의 ID
        - required: true

        **board_string**

        - name: board_string
        - in: json
        - description: 수정할 설명문의 새로운 board
        - required: false

        **content**

        - name: content
        - in: json
        - description: 수정할 설명문의 새로운 본문
        - required: false

    **Example request**:

    .. sourcecode:: http

        PUT /content/modify_content/1 HTTP/1.1
        Host: localhost
        Content-Type: application/json

            {
                "board_string": "linear_regression",
                "content": "linear regression is bad"
            }

    **Example response**:

    .. sourcecode:: http

        HTTP/1.1 200 OK

:resheader Content-Type: application/json
:status:
    - 200: 성공적으로 요청을 수행함
    - 400: 수정할 사항이 없음
    - 404: 수정하려는 본문이 존재하지 않음, 수정할 board가 존재하지 않음
    - 500: 알 수 없는 오류 발생

/content/get_content/<string:board_string> (GET)
--------------------------------------------------

    **특정 board 내의 모든 설명문을 반환**

    :Paramaters:

        **board_string**

        - name: board_string
        - in: path
        - description: 가져올 설명문들의 board
        - required: true

    **Example request**:

    .. sourcecode:: http

        GET /content/get_content/linear_regression HTTP/1.1
        Host: localhost

    **Example response**:

    .. sourcecode:: http

        HTTP/1.1 200 OK
        Content-Type: application/json

            {
                {
                    "id": 3,
                    "content: "Linear_regression is good"
                },
                {
                    "id": 4,
                    "content": "There is no reason"
                }
            }

:resheader Content-Type: application/json
:status:
    - 200: 성공적으로 요청을 수행함
    - 404: 가져오려는 board가 존재하지 않음
    - 500: 알 수 없는 오류 발생
