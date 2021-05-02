from flask import Blueprint
from methods import board
from methods.board import if_board_exist
from constants import messages
from . import is_api

ai_board_blueprint = Blueprint('AI_Board', __name__)


@ai_board_blueprint.route('/add_board/<string:board_string>', methods=['POST'])
def add_board(board_string: str):
    if if_board_exist(board_string):
        return {"error": messages.already_exists.format('board')}, 400

    status, message, status_code = board.add_board(board_string)

    if not status:
        return {'error': message}, status_code

    else:
        return {'created': message}


@ai_board_blueprint.route('/delete_board/<string:board_string>', methods=['DELETE'])
def delete_board(board_string: str):
    if not if_board_exist(board_string):
        return {"error": messages.no_exists.format('board')}, 404

    status, message, status_code = board.delete_board(board_string)

    if not status:
        return {'error': message}, status_code

    else:
        return {'message': message}


@ai_board_blueprint.route('/modify_board/<string:board_string>', methods=['PUT'])
@is_api(required_keys=['new_board_string'], input_type='json')
def modify_board(data, board_string: str):
    if not if_board_exist(board_string):
        return {"error": messages.no_exists.format('board')}, 404

    status, message, status_code = board.modify_board(board_string, **data)

    if not status:
        return {'error': message}, status_code

    else:
        return {'message': message}


@ai_board_blueprint.route('/get_board_list', methods=['GET'])
@is_api(acceptable_keys=[])
def get_board_list(data):
    return board.get_board_list()
