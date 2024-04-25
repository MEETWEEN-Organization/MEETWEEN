import { ComponentPropsWithRef } from 'react';

import { getPanelStyle, getTabStyle } from './Tab.style';

export interface ITab extends ComponentPropsWithRef<'li'> {
  text: string;
  tabId: string | number;
  selectedId: string | number;
  changeSelect: (tabId: string | number) => void;
}

const Tab = ({ text, tabId, selectedId, changeSelect, ...props }: ITab) => {
  const handleSelect = () => {
    changeSelect(tabId);
  };

  const handleEnterKeyPress = (event: React.KeyboardEvent<HTMLLIElement>) => {
    if (event.key === 'Enter') {
      changeSelect(tabId);
    }
  };

  return (
    <div>
      <li
        {...props}
        role="tab"
        tabIndex={0}
        onClick={handleSelect}
        onKeyDown={handleEnterKeyPress}
        css={[getTabStyle(tabId === selectedId)]}
      >
        {text}
      </li>
      <div css={getPanelStyle(tabId === selectedId)} role="tabpanel" />
    </div>
  );
};

export default Tab;
