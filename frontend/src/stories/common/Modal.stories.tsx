import type { Meta, StoryObj } from '@storybook/react';

import Box from '@/components/common/Box/Box';
import Button from '@/components/common/Button/Button';
import Heading from '@/components/common/Heading/Heading';
import Modal from '@/components/common/Modal/Modal';
import Text from '@/components/common/Text/Text';

import { useOverlay } from '@/hooks/common';

const meta = {
  title: 'Common/Modal',
  component: Modal,
  parameters: {
    layout: 'centered',
  },

  args: {
    isOpen: true,
    close: () => {},
  },
} satisfies Meta<typeof Modal>;

export default meta;
type Story = StoryObj<typeof meta>;

export const CloseButton: Story = {
  render: () => {
    const { isOpen, open, close } = useOverlay();

    return (
      <>
        <Button onClick={open}>Open a Modal</Button>
        <Modal isOpen={isOpen} close={close} hasCloseBtn={true}>
          <div>
            <Heading size="large">hi</Heading>
            <Text size="medium">
              The textual content of Element or Attr. If an element has no markup within its
              content, it has a single child implementing Text that contains the element's text.
              However, if the element contains markup, it is parsed into information items and Text
              nodes that form its children.
            </Text>
          </div>
        </Modal>
      </>
    );
  },
};

export const CloseByBackdrop: Story = {
  render: () => {
    const { isOpen, open, close } = useOverlay();

    return (
      <>
        <Button onClick={open}>Open a Modal</Button>
        <Modal isOpen={isOpen} close={close} isClosableByBackdrop={true}>
          <div>
            <Heading size="large">hi</Heading>
            <Text size="medium">
              The textual content of Element or Attr. If an element has no markup within its
              content, it has a single child implementing Text that contains the element's text.
              However, if the element contains markup, it is parsed into information items and Text
              nodes that form its children.
            </Text>
          </div>
        </Modal>
      </>
    );
  },
};

export const LongTextModal: Story = {
  render: () => {
    const { isOpen, open, close } = useOverlay();

    return (
      <>
        <Button onClick={open}>Open a Modal</Button>
        <Modal isOpen={isOpen} close={close} isClosableByBackdrop={true}>
          <div>
            <Heading size="large">hi</Heading>
            <Text size="medium">
              The textual content of Element or Attr. If an element has no markup within its
              content, it has a single child implementing Text that contains the element's text.
              However, if the element contains markup, it is parsed into information items and Text
              nodes that form its children. The textual content of Element or Attr. If an element
              has no markup within its content, it has a single child implementing Text that
              contains the element's text. However, if the element contains markup, it is parsed
              into information items and Text nodes that form its children. The textual content of
              Element or Attr. If an element has no markup within its content, it has a single child
              implementing Text that contains the element's text. However, if the element contains
              markup, it is parsed into information items and Text nodes that form its children. The
              textual content of Element or Attr. If an element has no markup within its content, it
              has a single child implementing Text that contains the element's text. However, if the
              element contains markup, it is parsed into information items and Text nodes that form
              its children.
            </Text>
          </div>
        </Modal>
      </>
    );
  },
};

export const PreventScroll: Story = {
  render: () => {
    const { isOpen, open, close } = useOverlay();

    return (
      <>
        <Button onClick={open}>Open a Modal</Button>
        <Box
          styles={{
            width: '200px',
            height: '100vh',
          }}
        />
        <Modal isOpen={isOpen} close={close} isClosableByBackdrop={true}>
          <div>
            <Heading size="large">hi</Heading>
            <Text size="medium">
              The textual content of Element or Attr. If an element has no markup within its
              content, it has a single child implementing Text that contains the element's text.
              However, if the element contains markup, it is parsed into information items and Text
              nodes that form its children. The textual content of Element or Attr. If an element
              has no markup within its content, it has a single child implementing Text that
              contains the element's text. However, if the element contains markup, it is parsed
              into information items and Text nodes that form its children. The textual content of
              Element or Attr.
            </Text>
          </div>
        </Modal>
      </>
    );
  },
};
