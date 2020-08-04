//
//  RegisterDelegate.m
//  Runner
//
//  Created by 林辉 on 2020/8/4.
//  Copyright © 2020 The Chromium Authors. All rights reserved.
//

#import "RegisterDelegate.h"
#import <WechatOpenSDK/WXApi.h>
#import "FluwxResponseHandler.h"

@implementation RegisterDelegate

- (instancetype)initWithUrl:(NSURL *)url
{
    self = [super init];
    if (self) {
        self.url = url;
    }
    return self;
}


- (void) registerCallback{
    [WXApi handleOpenURL:self.url delegate:[FluwxResponseHandler defaultManager]];
}

@end
