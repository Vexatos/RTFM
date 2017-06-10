# Welcome!

Hey, you! Yes, the one who's currently reading this! Thanks for using RTFM!

## Getting Started

Now then, you'll probably want to get started writing documentation. For that, check our `rtfm.cfg` in your instance's `config/` directory. In the `manual` category, you can add new tabs to this manual. The configuration of a tab consists of the texture or item it displays and the text you see when looking at it along with the page that clicking it will open.

## Adding Things

Pages are mostly written in ordinary markdown, or at least a subset thereof. For example, you can link to another page by writing `[some kind of link](example.md)` into your markdown file; of course, subdirectories work too. While relative links work exactly as you would expect, absolute links always start with the language directory. You can use `%LANGUAGE%` here which resolves into whichever language the player has currently selected.

Furthermore, this mod allows displaying items, blocks or any texture added by a mod or resource pack. Simply use the markdown image syntax, adding any of the supported prefixes to the image "link".

Displaying an item: `![Tooltip to display](item:minecraft:potato@0)`
![Tooltip to display](item:minecraft:potato@0)

Displaying a block: `![Another tooltip to display](block:minecraft:stone@0)`
![Another tooltip to display](block:minecraft:stone@0)

Display items from the ore dictionary: `![A lot of tooltips](oredict:ingotGold)`
![A lot of tooltips](oredict:ingotGold)

Display a texture: `![Yet Another Tooltip](forge:textures/item/bucket_cover.png)`
![Just Enough Tooltips](forge:textures/items/bucket_cover.png)

There's more stuff you can do. Many parts of ordinary markdown syntax are supported, so just mess around with it.
