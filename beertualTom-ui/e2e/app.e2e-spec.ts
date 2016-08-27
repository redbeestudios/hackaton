import { BeertualTomUiPage } from './app.po';

describe('beertual-tom-ui App', function() {
  let page: BeertualTomUiPage;

  beforeEach(() => {
    page = new BeertualTomUiPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
