import { ComponentPropsWithRef } from 'react';

import { getPanelStyle, getTabStyle } from '@/components/common/Tab/Tab.style';

export interface TabProps extends ComponentPropsWithRef<'li'> {
  text: string;
  tabId: string | number;
  selectedId: string | number;
  changeSelect: (tabId: string | number) => void;
}

const Tab = ({ text, tabId, selectedId, changeSelect, ...props }: TabProps) => {
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
        aria-selected={tabId === selectedId}
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
