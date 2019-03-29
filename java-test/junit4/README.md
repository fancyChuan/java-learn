## java测试框架

### 单元测试JUnit
传统测试方式：程序员在main方法中写测试逻辑，然后人工判断。这种方式很low，而且不能写多个

最常用的单元测试框架是JUnit, TestNG也挺常用的

Junit4放弃旧的断言，使用hamcrest断言
- assertThat，能够替换所有类似于 assertTure() assertEquals() 等等
- 使用hamcrest的匹配方法，更自然
- 示例
```
a)
assertThat( n, allOf( greaterThan(1), lessThan(15) ));  //  1<n<15
assertThat( n, anyOf( greaterThan(16), lessThan(8) ) ); // n>16 | n<8
assertThat( n, anything() );
assertThat( str, is( "bjsxt" ) ); //str = 
assertThat( str, not( "bjxxt" ) e

b)     
assertThat( str, containsString( "bjsxt" ));
assertThat( str, endsWith("bjsxt" ) ); 
assertThat( str, startsWith( "bjsxt" ) ); 
assertThat( n, equalTo( nExpected ) ); 
assertThat( str, equalToIgnoringCase( "bjsxt" ) ); 
assertThat( str, equalToIgnoringWhiteSpace( "bjsxt" ) );

c)     
assertThat( d, closeTo( 3.0, 0.3 ) ); // 3+- 0.3
assertThat( d, greaterThan(3.0) );
assertThat( d, lessThan (10.0) );
assertThat( d, greaterThanOrEqualTo (5.0) );
assertThat( d, lessThanOrEqualTo (16.0) );

d)   Map test
assertThat(map, hasEntry( "bjsxt", "bjsxt" ) );
assertThat( iterable, hasItem ( "bjsxt" ) );
assertThat( map, hasKey ( "bjsxt" ) );
assertThat( map, hasValue ( "bjsxt" ) );
```

- Failure 和 Error
    - Failure是指测试失败
    - Error是指测试程序本身出错 eg: int a = 8/0
    
- Junit4 Annotation
    - @Test 测试方法
        - (expected=XXException.class) 预期抛出xx异常
        - (timeout=xxx) 希望方法在多长时间内跑完
    - @Ignore 忽略的测试方法
    - @Before 在运行测试方法前执行
    - @After  在运行测试方法后执行
    - @BeforeClass 
    - @AfterClass 
    
- 运行多个测试
    - 运行测试类
    - 运行测试包
    - 运行所有测试包