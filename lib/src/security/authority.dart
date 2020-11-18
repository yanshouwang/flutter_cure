part of security;

/// 权限
abstract class Authority {
  /// 检查
  Future<AuthorityState> checkAsync();

  /// 申请
  Future<AuthorityState> requestAsync();

  /// 日历
  static Authority get calendar => _Authority(0);

  /// 相机
  static Authority get camera => _Authority(1);

  /// 通讯录
  static Authority get contacts => _Authority(2);

  /// 位置（所有时间）
  static Authority get locationAlways => _Authority(3);

  /// 位置（使用时）
  static Authority get locationWhenUse => _Authority(4);

  /// 麦克风
  static Authority get microphone => _Authority(5);

  /// 手机
  static Authority get phone => _Authority(6);

  /// 传感器
  static Authority get sensors => _Authority(7);

  /// 短信
  static Authority get sms => _Authority(8);

  /// 存储
  static Authority get storage => _Authority(9);
}

class _Authority implements Authority {
  final int _index;
  final MethodChannel _channel;

  _Authority(this._index)
      : _channel = MethodChannel('dev.yanshouwang.cure/authority');

  @override
  Future<AuthorityState> checkAsync() async {
    final index = await _channel.invokeMethod('checkAsync', this._index);
    final state = AuthorityState.values[index];
    return state;
  }

  @override
  Future<AuthorityState> requestAsync() async {
    final index = await _channel.invokeMethod('requestAsync', this._index);
    final state = AuthorityState.values[index];
    return state;
  }
}
