from connector import MySQL
from constants import messages


def if_content_exist(content_id: int):
    sql = MySQL()

    result = sql.query('SELECT COUNT(*) FROM contents WHERE id=%s', (content_id,))

    if result[0][0] == 0:
        return False

    return True


def add_content(board_string: str, content: str):
    sql = MySQL()

    if board_string is None or content is None:
        return False, messages.no_required_args, 400

    sql.transaction.start()
    try:
        sql.query('INSERT INTO contents (board, content) VALUE (%s, %s)', (board_string, content,))
    except:
        sql.transaction.rollback()
        return False, messages.exception_occurred, 500
    else:
        inserted = sql.query('SELECT LAST_INSERT_ID()')[0][0]
        sql.transaction.commit()
        return True, messages.add_success.format('content'), 200


def delete_content(content_id: int):
    sql = MySQL()

    sql.transaction.start()
    try:
        sql.query('DELETE FROM contents WHERE id=%s', (content_id,))
    except:
        sql.transaction.rollback()
        return False, messages.exception_occurred, 500
    else:
        sql.transaction.commit()
        return True, messages.content_delete_success, 200


def modify_contents(content_id: int, board_string=None, content=None):
    sql = MySQL(dict_cursor=True)

    result = sql.query('SELECT id, board, content FROM contents WHERE id=%s', (content_id,))

    if board_string is not None:
        if result[0]['board'] == board_string:
            index = None

    if content is not None:
        if result[0]['content'] == content:
            content = None

    to_modify = [f'{k}={sql.escape(v)}' for k, v in locals().items() if v in [board_string, content] and v is not None]

    if len(to_modify) == 0:
        return False, messages.no_modification_made, 400

    sql.transaction.start()
    try:
        sql.query(f"UPDATE contents SET {','.join(to_modify)} WHERE id=%s", (content_id,))
    except Exception as ex:
        print(ex)
        sql.transaction.rollback()
        return False, messages.exception_occurred, 500
    else:
        sql.transaction.commit()
        return True, "modification_complete", 200


def get_contents(board_string: str):
    sql = MySQL(dict_cursor=True)

    result = list(sql.query('SELECT id, content FROM contents WHERE board=%s', (board_string,)))
    response = list()

    for i in result:
        content = dict()
        content['id'] = i['id']
        content['content'] = i['content']
        response.append(content)

    return response
