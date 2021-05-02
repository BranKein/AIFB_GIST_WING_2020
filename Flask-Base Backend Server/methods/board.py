from connector import MySQL
from constants import messages


def if_board_exist(board_string: str):
    sql = MySQL()

    result = sql.query('SELECT COUNT(*) FROM board WHERE description=%s', (board_string,))

    if result[0][0] == 0:
        return False

    return True


def add_board(board_string: str):
    sql = MySQL()

    if board_string is None:
        return False, messages.no_required_args, 400

    sql.transaction.start()
    try:
        sql.query('INSERT INTO board (description) VALUE (%s)', (board_string, ))
    except:
        sql.transaction.rollback()
        return False, messages.exception_occurred, 500
    else:
        inserted = sql.query('SELECT LAST_INSERT_ID()')[0][0]
        sql.transaction.commit()
        return True, inserted, 200


def delete_board(board_string: str):
    sql = MySQL()

    sql.transaction.start()
    try:
        sql.query('DELETE FROM board WHERE description=%s', (board_string,))
    except:
        sql.transaction.rollback()
        return False, messages.exception_occurred, 500
    else:
        sql.transaction.commit()
        return True, messages.delete_success.format('board'), 200


def modify_board(board_string: str, new_board_string=None):
    sql = MySQL(dict_cursor=True)

    result = sql.query('SELECT id, description FROM board WHERE description=%s', (board_string,))

    if new_board_string is not None:
        if result[0]['description'] == new_board_string:
            return False, "no_modification_made", 400

    sql.transaction.start()
    try:
        sql.query('UPDATE board SET description=%s WHERE description=%s', (new_board_string, board_string,))
    except:
        sql.transaction.rollback()
        return False, messages.exception_occurred, 500
    else:
        sql.transaction.commit()
        return True, new_board_string, 200


def get_board_list():
    sql = MySQL(dict_cursor=True)
    return list(sql.query('SELECT id, description FROM board'))
