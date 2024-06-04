import { Global, ThemeProvider } from '@emotion/react';
import type { Preview } from '@storybook/react';

import React from 'react';
import { MemoryRouter } from 'react-router-dom';

import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

import { RecoilRoot } from 'recoil';

import { GlobalStyle } from '../src/styles/GlobalStyle';
import { Theme } from '../src/styles/theme/theme';

const preview: Preview = {
  parameters: {
    actions: { argTypesRegex: '^on[A-Z].*' },
    controls: {
      matchers: {
        color: /(background|color)$/i,
        date: /Date$/i,
      },
    },
  },
};

export default preview;

const queryclient = new QueryClient();

export const decorators = [
  (Story) => (
    <QueryClientProvider client={queryclient}>
      <RecoilRoot>
        <MemoryRouter initialEntries={['/']}>
          <ThemeProvider theme={Theme}>
            <Global styles={GlobalStyle} />
            <Story />
          </ThemeProvider>
        </MemoryRouter>
      </RecoilRoot>
    </QueryClientProvider>
  ),
];
