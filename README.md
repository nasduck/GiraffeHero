![banner](https://raw.githubusercontent.com/nasduck/GiraffeHero/dev/art/banner.png)

[![API](https://img.shields.io/badge/GiraffeHero-v1.0.1-brightgreen.svg?style=flat)](https://github.com/nasduck/GiraffeHero/releases)&ensp;
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)&ensp;
[![API](https://img.shields.io/badge/License-Apche2.0-brightgreen.svg?style=flat)](https://github.com/nasduck/GiraffeHero/blob/master/LICENSE)

**GiraffeHero 【鹿男】是我们结合 iOS 和 Android 对于复杂页面的设计和理解构建出来的第三方库. 理论上任何常规复杂页面都可以通过鹿男来实现. 但和其它类似实现多样式 RecycleView 的库不同的是对于复杂页面的设计划分和理解.**

在我们的构想中, 所有页面都是由至少一个 `Section` 构成的. 每个 `Section` 都有独立的 `Header` 和 `Footer`, 以及内部至少1个以上的元素构成(我们称为 `Row`):

// todo 复杂页面长图加标注

理论上不同的 `Section` 代码逻辑应该是完全独立的, 可以由不同的人员去开发和维护, 当然也可以在不同的页面间复用. 

当刷新页面时, 比起原生的 `notifyDataSetChanged`, `notifyItemRangeChanged` 以及 `notifyItemChanged`. 这个库可以帮助你实现单独刷新某个 `Section`, 某个 `Section` 当中的某个 `Row`. 或者仅仅是刷新这个 `Section` 独有的 `Header` 和 `Footer`. 尤其适合于页面内容是由不同的 API 返回的. 不同的 API 返回只需刷新自己对应的 `Section`. 如果个别 API 还未返回或出错, 对应的 `Section` 将不会显示.

欢迎尝试【鹿男】, 让每一个页面都符合代码强迫症的美学 :D

## 特点

* 全新的复杂页面实现理念和刷新机制
* 各区块代码完全解耦, 可多页面复用
* 支持每个区块都有独立的 Header 和 Footer
* 支持页面无内容提示
* 支持上拉加载更多
* 支持列表进入动画
* 支持列表元素动画

## 使用指南

## Contributer

* [Chuan DONG](https://github.com/DONGChuan)
* [Lihao Zhou](https://github.com/redrain39)
* [Si Cheng](1103990937@qq.com)(Art Designer)

## LICENSE
```
   Copyright (2019) Chuan Dong, Lihao Zhou, Si Cheng

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
