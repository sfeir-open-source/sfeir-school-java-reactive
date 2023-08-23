import {SfeirThemeInitializer} from '../web_modules/sfeir-school-theme/sfeir-school-theme.mjs';

// One method per module
function schoolSlides() {
  return [
    '00-school/00-TITLE.md',
    '00-school/01-Overview.md'];
}

function speakers() {
  return [
    '00-school/02-speaker-npx.md',
    '00-school/03-speaker-smr.md'];
}

function introReactorSlides() {
  return [
    '01-intro-reactor/00-presentation.md',
    '01-intro-reactor/01-plan.md',
    '01-intro-reactor/02-prerequis.md',
    '01-intro-reactor/03-basics.md',
    '01-intro-reactor/04-histoire.md',
    '01-intro-reactor/05-explications.md'];
}

function formation() {
  return [
    //
    ...schoolSlides(), //
    ...speakers(), //
    ...introReactorSlides(), //
  ].map((slidePath) => {
    return {path: slidePath};
  });
}

SfeirThemeInitializer.init(formation);
