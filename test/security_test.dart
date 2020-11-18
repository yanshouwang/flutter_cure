import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_cure/security.dart';

void main() {
  const MethodChannel channel = MethodChannel('dev.yanshouwang.cure/authority');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return 1;
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('checkAsync', () async {
    final actual = await Authority.calendar.checkAsync();
    final matcher = AuthorityState.authorized;
    expect(actual, matcher);
  });

  test('requestAsync', () async {
    final actual = await Authority.calendar.requestAsync();
    final matcher = AuthorityState.authorized;
    expect(actual, matcher);
  });
}
