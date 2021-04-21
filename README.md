# KWallet

> A digital wallet for all your credit card needs

## About
Did you ever have to get your credit card info but you didn't have your wallet near you?
Or you just have lots of credit cards and want them all in one place?

KWallet aims to solve this exact problem (in the future) by keeping an overview of your cards in an app.
Right now this is only my personal playground to get familiar with Jetpack Compose though

---

## Getting started
In theory you should just be able to clone this project, open your favorite IDE and run it. It currently only supports Android so you need to have JAVA installed. I chose most dependencies carefully so multi-platform _should_ be possible with some modifications

---

## So what does it look like?
Awesome! Jokes aside, I only just started this project and focused on functionality more than UI/UX. I do plan on making it better once I have all the basic functionality working though. Whenever it looks actually visibly appealing, you'll get your screenshots, I promise.
Can't tolerate the current "design"? I'm always happy for any contributions!

---

## Current features, aka what's working
Visually, you just have a screen and a card that brings you to a card creation screen

Under the hood, there's already a simple database structure, navigation is working and you can **flip cards**. Dependency injection is also working and everything is a Composable with Preview features. No more XML views

---

## Planned features
These are in the current order I plan to work on them. If you have any other ideas feel free to open an issue
### Current focus
- Think about different ways of presenting the card creation layer. A new screen seems overkill

### Future thoughts
- Set up a GitHub action
- UI overhaul, custom color scheme, material or glassmorph design, animations and transitions
- Add some tests
- Make the whole project more modular
- Add other types of cards, i.e. debit cards, insurance, ID?
- Add payment details screen for each credit card
- Add biometric/password lock so your credit cards are actually secure
- Release it on F-Droid

---

## Can I contribute?
Yes please! Contributions are always welcome. The code is probably a bit messy since this is my first Jetpack Compose project (and Android project in general) so I'd actually love some feedback and suggestions.

---

## License
[MIT](LICENSE.md)

For a full overview of dependencies and their licenses also see [third-party-licenses](/third-party-licenses)