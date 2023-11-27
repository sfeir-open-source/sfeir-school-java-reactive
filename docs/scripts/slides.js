import {SfeirThemeInitializer} from '../web_modules/sfeir-school-theme/sfeir-school-theme.mjs';

// One method per module
function schoolSlides() {
  return [
    '00-school/00-TITLE.md',
    '00-school/02-speaker-npx.md',
    //'00-school/03-speaker-smr.md',
    '00-school/01-Overview.md'];
}

function introReactorSlides() {
  return [
    '01-intro-reactor/00-presentation.md',
    '01-intro-reactor/01-prerequis.md',
    '01-intro-reactor/02-histoire.md',
    '01-intro-reactor/03-explications.md',
    '01-intro-reactor/04-projectreactor.md',
    '01-intro-reactor/05-utilisation.md',
    '01-intro-reactor/06-integrations.md',
    '01-intro-reactor/07-dependances.md',
    '01-intro-reactor/08-specificites.md'];
}

function basicsSlides() {
  return [
    '02-basics/00-flux.md',
    '02-basics/01-mono.md',
    '02-basics/02-creationFlux.md',
    '02-basics/02b-creationMono.md',
    '02-basics/03-souscription.md',
    '02-basics/03b-alternatives.md',
    '02-basics/03c-workshop1-partie1.md',
    '02-basics/04-transformer.md',
    '02-basics/05-filtrer.md',
    '02-basics/06-combiner.md',
    '02-basics/07-workshop1-partie2.md'
  ];
}

function advancedSlides() {
  return [
    '03-advanced/00-intercepter-erreurs.md',
    '03-advanced/00b-reprise-erreurs.md',
    '03-advanced/00c-transformation-erreurs.md',
    '03-advanced/01-back-pressure.md',
    '03-advanced/02-workshop2.md'
  ];
}

function conclusionSlides() {
  return [
    '04-conclusion/00-pitfalls.md',
    '04-conclusion/01-conclusion.md',
    '04-conclusion/02-perspectives.md',
    '04-conclusion/99-satisfaction-form.md'
  ];
}

function formation() {
  return [
    //
    ...schoolSlides(), //
    ...introReactorSlides(), //
    ...basicsSlides(), //
    ...advancedSlides(), //
    ...conclusionSlides() //
  ].map((slidePath) => {
    return {path: slidePath};
  });
}

SfeirThemeInitializer.init(formation);
