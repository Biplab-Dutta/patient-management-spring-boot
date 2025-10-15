package com.zoroxnekko.stack

import software.amazon.awscdk.App
import software.amazon.awscdk.Stack
import software.amazon.awscdk.StackProps

class LocalStack(
    private val scope: App,
    private val id: String,
    private val props: StackProps,
) : Stack(scope, id, props) {

}