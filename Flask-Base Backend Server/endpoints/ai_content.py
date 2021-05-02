from flask import Blueprint
from . import is_api
from methods import content
from methods.board import if_board_exist
from constants import messages

ai_content_blueprint = Blueprint('AI_Content', __name__)


@ai_content_blueprint.route('/test', methods=['GET'])
def test():
    return if_board_exist('1. 머신러닝')


@ai_content_blueprint.route('/add_content', methods=['POST'])
@is_api(required_keys=['board_string', 'content'], input_type='json')
def add_content(data):
    if not if_board_exist(data['board_string']):
        return {"error": messages.no_exists.format('board')}, 404

    status, message, status_code = content.add_content(**data)

    if not status:
        return {'error': message}, status_code

    else:
        return {'message': message}


@ai_content_blueprint.route('/delete_content/<int:content_id>', methods=['DELETE'])
def delete_content(content_id: int):
    if not content.if_content_exist(content_id):
        return {"error": messages.no_exists.format('content')}, 404

    status, message, status_code = content.delete_content(content_id)

    if not status:
        return {'error': message}, status_code

    else:
        return {'message': message}


@ai_content_blueprint.route('/modify_content/<int:content_id>', methods=['PUT'])
@is_api(acceptable_keys=['board_string', 'content'], input_type='json')
def modify_content(data, content_id: int):
    if not content.if_content_exist(content_id):
        return {"error": messages.no_exists.format('content')}, 404

    if not if_board_exist(data['board_string']):
        return {"error": messages.no_exists.format('board')}, 404

    status, message, status_code = content.modify_contents(content_id, **data)

    if not status:
        return {'error': message}, status_code

    else:
        return {'message': message}


@ai_content_blueprint.route('/get_content/<string:board_string>', methods=['GET'])
@is_api(acceptable_keys=[])
def get_content(data, board_string: str):
    if not if_board_exist(board_string):
        return {"error": messages.no_exists.format('board')}, 404

    return content.get_contents(board_string)
