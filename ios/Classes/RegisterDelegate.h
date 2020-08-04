//
//  RegisterDelegate.h
//  Runner
//
//  Created by 林辉 on 2020/8/4.
//  Copyright © 2020 The Chromium Authors. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface RegisterDelegate : NSObject
@property(nonatomic) NSURL * url;
- (instancetype)initWithUrl:(NSURL *)url;
- (void) registerCallback;
@end

NS_ASSUME_NONNULL_END
