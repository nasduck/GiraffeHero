![banner](https://raw.githubusercontent.com/nasduck/GiraffeHero/dev/art/banner.png)

[![API](https://img.shields.io/badge/GiraffeHero-v1.0.1-brightgreen.svg?style=flat)](https://github.com/nasduck/GiraffeHero/releases)&ensp;
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)&ensp;
[![API](https://img.shields.io/badge/License-Apche2.0-brightgreen.svg?style=flat)](https://github.com/nasduck/GiraffeHero/blob/master/LICENSE)

>[中文文档](https://github.com/nasduck/GiraffeHero/blob/dev/README-CN.md)

**GiraffeHero is a third-party lib built with iOS and Android’s design and understanding of complex pages. In theory, any complex page can be built by GiraffeHero. However, unlike other libs in implementing multi-type RecycleView, GiraffeHero has different design and understanding of complex pages.**

In our conception, all pages are made up of at least one `Section`. Each `Section` has a separate `Header` and `Footer`, and at least one element inside (we call it `Row`):

#### GiraffeHero structure diagram：
![diagram](https://github.com/nasduck/GiraffeHero/blob/dev/art/section%E7%A4%BA%E6%84%8F%E5%9B%BE.jpg?raw=true)

Theoretically, code logic of different Section could be completely decoupled, which can be developed and maintained by different people, and it can also be reused among different pages.

Compared to the native `notifyDataSetChanged`, `notifyItemRangeChanged` and `notifyItemChanged`, this lib can help you to refresh a Section, a Row in a Section or just refresh the Header and Footer of a Section. It is especially suitable for page content that is returned by different APIs. Different APIs only need to refresh their corresponding Section when data returned. If one API return errors or just not returned, the corresponding Section will not be displayed.

Welcome to use GiraffeHero, making every page meets the aesthetics of code obsession. :D

## Features

* Brand new complex pages implement concept and refresh mechanism
* Each Section is completely decoupled and can be reused in different pages
* Each Section has separate Header and Footer
* Support empty view for pages without content 
* Support for loading more
* Support list animation
* Support item animation

## Dependency

Step 1: Add `jitpack` to `build.gradle`.

```
allprojects {
	repositories {
		...
		maven { url 'https://www.jitpack.io' }
	}
}
```

Step 2: Add dependencies:

```
   implementation 'com.github.nasduck:GiraffeHero:1.1.0'
```

## User Guide

Please check [Wiki](https://github.com/nasduck/GiraffeHero/wiki)

## Suggestion&Question

Welcome to send emails to dongchuanyz@163.com

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
