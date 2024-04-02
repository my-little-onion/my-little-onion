import { useState } from 'react';

import ModalContent from '@/components/Modal';

const useModal = () => {
  const [isOpen, setIsOpen] = useState<boolean>(false);

  const openModal = () => {
    setIsOpen(true);
  };

  const closeModal = () => {
    setIsOpen(false);
  };

  interface ModalProps {
    children: React.ReactNode;
  }

  const Modal = ({ children }: ModalProps) => {
    return (
      <ModalContent onClose={closeModal} isOpen={isOpen}>
        {children}
      </ModalContent>
    );
  };

  return { Modal, openModal, closeModal };
};

export default useModal;
