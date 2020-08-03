const String _transaction = "transaction";
const String _openId = "openId";

typedef BaseWeChatRequest _WeChatRequestInvoker(Map argument);

Map<String, _WeChatRequestInvoker> _nameAndRequestMapper = {
  "onShowMessageReq": (Map argument) =>
      WeChatShowMessageRequest.fromMap(argument),
};

class BaseWeChatRequest {
  final String transaction;
  final String openId;

  BaseWeChatRequest._(this.transaction, this.openId);


  factory BaseWeChatRequest.create(String name, Map argument) =>
      _nameAndRequestMapper[name](argument);
}

class WeChatShowMessageRequest extends BaseWeChatRequest {
  final String extInfo;

  WeChatShowMessageRequest.fromMap(Map map)
      : extInfo = map["extInfo"],
        super._(map[_transaction], map[_openId]);
}
