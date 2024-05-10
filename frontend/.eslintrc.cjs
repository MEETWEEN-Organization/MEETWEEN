module.exports = {
  env: {
    browser: true,
    es6: true,
    node: true,
  },
  parser: '@typescript-eslint/parser',
  plugins: ['@typescript-eslint', 'prettier', 'import'],
  extends: [
    'plugin:import/errors',
    'plugin:import/warnings',
    'plugin:react/jsx-runtime',
    'plugin:react-hooks/recommended',
    'plugin:@typescript-eslint/recommended', // import/recommend
    'plugin:jsx-a11y/recommended',
    'plugin:prettier/recommended',
    'prettier',
    'plugin:storybook/recommended',
    'import',
  ],
  parserOptions: {
    ecmaVersion: 2020,
    sourceType: 'module',
    ecmaFeatures: {
      jsx: true,
    },
  },
  rules: {
    'linebreak-style': 0, // react self closing-comp
    'import/no-dynamic-require': 0,
    'import/no-unresolved': 'off',
    'import/prefer-default-export': 0,
    'global-require': 0,
    'import/no-extraneous-dependencies': 0,

    'react/jsx-props-no-spreading': 0,
    'react/forbid-prop-types': 0,
    'react/function-component-definition': [
      'error',
      {
        namedComponents: 'arrow-function',
        unnamedComponents: 'arrow-function',
      },
    ],
    'react/jsx-filename-extension': [2, { extensions: ['.js', '.jsx', '.ts', '.tsx'] }],
    'import/extensions': 0,
    'no-use-before-define': 0,
    '@typescript-eslint/no-empty-interface': 1,
    '@typescript-eslint/no-explicit-any': 0,
    '@typescript-eslint/no-var-requires': 0,
    'no-shadow': 'off',
    'react/prop-types': 0,
    'no-empty-pattern': 0,
    'no-alert': 0,
    'react-hooks/exhaustive-deps': 0,
    'no-restricted-imports': [
      'error',
      {
        patterns: ['.*'],
      },
    ],
  },
  settings: {
    'import/parsers': {
      '@typescript-eslint/parser': ['.ts', '.tsx'],
    },
    'import/resolver': {
      typescript: 'tsconfig.json',
    },
  },
};
