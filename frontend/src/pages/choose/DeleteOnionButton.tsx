import { useState } from 'react';

import theme from '@/styles/theme';
import { voiceType } from '@/types/onion';
import useModal from '@/hooks/useModal';
import { deleteOnion } from '@/services/onion';

import Button from '@/components/Button';

interface DeleteOnionButtonProps {
  onionId: number;
  fetchData: () => void;
}

const DeleteOnionButton = ({ onionId, fetchData }: DeleteOnionButtonProps) => {
  const [voices, setVoices] = useState<voiceType[]>([]);
  const { Modal, openModal } = useModal();

  const handleDeleteClick = async () => {
    const voiceData = await deleteOnion(onionId);
    setVoices(voiceData.data);
    openModal();
    fetchData();
  };

  return (
    <>
      <Button
        type='button'
        size='medium'
        color={theme.color.red}
        onClick={handleDeleteClick}
      >
        양파 까기
      </Button>
      <Modal>
        <div>내가 양파에게 했던 말</div>
        {voices.map((voice) => (
          <div key={voice.voice}>{voice.voice}</div>
        ))}
      </Modal>
    </>
  );
};

export default DeleteOnionButton;
