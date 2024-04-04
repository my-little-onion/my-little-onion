import { useState } from 'react';

import ModalContent from '@/components/Modal';

const useModal = () => {
  const [isOpen, setIsOpen] = useState<boolean>(false);

  const openModal = () => {
    setIsOpen(true);
  };

  const closeModal = (isDelete?: boolean) => {
    setIsOpen(false);
    if (isDelete) window.location.replace('/choose');
  };

  interface ModalProps {
    children: React.ReactNode;
    isDelete?: boolean;
  }

  const Modal = ({ children, isDelete }: ModalProps) => {
    return (
      <ModalContent onClose={() => closeModal(isDelete)} isOpen={isOpen}>
        {children}
      </ModalContent>
    );
  };

  return { Modal, openModal, closeModal };
};

export default useModal;
