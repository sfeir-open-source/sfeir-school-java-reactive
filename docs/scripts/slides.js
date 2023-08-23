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
    '01-intro-reactor/02-prerequis.md',
    '01-intro-reactor/04-histoire.md',
    '01-intro-reactor/05-explications.md'];
}

function basicsSlides() {
  return [
    '02-basics/00-initialisation.md',
    '02-basics/01-transformer.md',
    '02-basics/02-filtrer.md',
    '02-basics/03-combiner.md'
  ];
}

function advancedSlides() {
  return [
    '03-advanced/00-gestion-erreurs.md',
    '03-advanced/01-back-pressure.md'
  ];
}

function conclusionSlides() {
  return [
    '04-conclusion/00-pitfalls.md',
    '04-conclusion/01-conclusion.md'
  ];
}

function formation() {
  return [
    //
    ...schoolSlides(), //
    ...speakers(), //
    ...introReactorSlides(), //
    ...basicsSlides(), //
    ...advancedSlides(), //
    ...conclusionSlides() //
  ].map((slidePath) => {
    return {path: slidePath};
  });
}

SfeirThemeInitializer.init(formation);
