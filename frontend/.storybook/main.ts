import type { StorybookConfig } from '@storybook/react-vite';
import path from 'path';

const config: StorybookConfig = {
  stories: ['../src/**/*.mdx', '../src/**/*.stories.@(js|jsx|mjs|ts|tsx)'],
  addons: [
    '@storybook/addon-onboarding',
    '@storybook/addon-links',
    '@storybook/addon-essentials',
    '@chromatic-com/storybook',
    '@storybook/addon-interactions',
  ],
  framework: {
    name: '@storybook/react-vite',
    options: {},
  },
  docs: {
    autodocs: 'tag',
  },
  core: {
    builder: '@storybook/builder-vite',
  },

  async viteFinal(config, { configType }) {
    if (config.resolve) {
      config.resolve.alias = {
        ...config.resolve.alias,
        '@': path.resolve(__dirname, '../src'),
        '@components': path.resolve(__dirname, '../src/components'),
        '@type': path.resolve(__dirname, '../src/type'),
        '@hooks': path.resolve(__dirname, '../src/hooks'),
        '@styles': path.resolve(__dirname, '../src/styles'),
        '@constants': path.resolve(__dirname, '../src/constants'),
        '@assets': path.resolve(__dirname, '../src/assets'),
        '@stories': path.resolve(__dirname, '../src/stories'),
        '@utils': path.resolve(__dirname, '../src/utils'),
      };
    }

    return config;
  },
};
export default config;
