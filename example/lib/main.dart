import 'package:flutter/material.dart';

import 'package:flutter/services.dart';
import 'package:flutter_cure/security.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _state = AuthorityState.undetermined.toString();

  @override
  void initState() {
    super.initState();
    requestAuthority();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  void requestAuthority() async {
    String state;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      final result = await Authority.calendar.requestAsync();
      state = result.toString();
    } on PlatformException {
      state = 'requestAuthority error';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _state = state;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Authority state: $_state\n'),
        ),
      ),
    );
  }
}
